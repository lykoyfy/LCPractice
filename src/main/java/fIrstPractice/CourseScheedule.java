package fIrstPractice;

import java.util.*;

public class CourseScheedule {

    public boolean canFinish(int numCourses, int[][] prerequisites){

        Node[] allNode = new Node[numCourses];

        for(int[] prerequiesite: prerequisites){
            if(allNode[prerequiesite[0]] == null){
                allNode[prerequiesite[0]] = new Node();
            }
            if(allNode[prerequiesite[1]] == null){
                allNode[prerequiesite[1]] = new Node();
            }

            allNode[prerequiesite[0]].prepreList.add(allNode[prerequiesite[1]]);
            allNode[prerequiesite[0]].inNums++;
        }

        Queue<Node> visitNodeQueue = new LinkedList<>();
        for(Node n : allNode){
            if(n == null)
                continue;

            if(n.inNums == 0)
                visitNodeQueue.add(n);
        }

        if(visitNodeQueue.size() == 0){
            return false;
        }

        for(Node n : visitNodeQueue){
            if(n.isInCycle(new HashSet<Node>())){
                return false;
            }
        }

        return true;
    }

    class Node{
        List<Node> prepreList = new LinkedList<>();

        int inNums = 0;

        boolean isInCycle(Set<Node> mark){
            for(Node n : prepreList){
                if(mark.contains(n)){
                    return true;
                }
                mark.add(n);
                n.isInCycle(mark);
            }
            return false;
        }
    }
}
