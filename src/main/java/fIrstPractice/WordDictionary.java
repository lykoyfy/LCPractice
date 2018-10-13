package fIrstPractice;

public class WordDictionary {
    private Node rootNode = new Node();

    public  WordDictionary(){

    }

    public void addWord(String word){
        char[] wordArray = word.toCharArray();
        rootNode.addChild(wordArray, 0);
    }

    public boolean search(String word){
        char[] wordArray = word.toCharArray();
        return rootNode.match(wordArray, 0);
    }

    public static void main(String[] args){
        WordDictionary wd = new WordDictionary();
        wd.addWord("add");
        wd.addWord("addx*");
        wd.addWord("ad**d");
        wd.addWord("adsdsdd");

        System.out.println(wd.search("ad***d"));
    }

    class Node{
        public char c;
        public boolean isEnd;
        public Node[] childrenNodes = new Node[27];

        boolean match(char[] searchWords, int index){
            if(index == searchWords.length)
                return isEnd;

            if(searchWords[index] == '*'){

                for(Node n : childrenNodes){
                    if(n != null){
                        if(n.match(searchWords, index+1))
                            return true;
                    }

                }
                return false;

            }else {
                int childIndex = searchWords[index]-'a';
                if(childrenNodes[childIndex] != null){
                    return childrenNodes[childIndex].match(searchWords, index+1);
                }else {
                    return false;
                }
            }

        }


        void addChild(char[] newWord, int index){
            if(index == newWord.length){
                isEnd = true;
                return;
            }
            int childIndex = 0;
            if(newWord[index] == '*'){
                childIndex = 26;
            }else {
                childIndex = newWord[index] - 'a';
            }
            if(childrenNodes[childIndex] == null){
                Node newNode = new Node();
                newNode.c = (char)('a' + childIndex);
                childrenNodes[childIndex] = newNode;
            }
            childrenNodes[childIndex].addChild(newWord, index+1);

        }
    }
}
