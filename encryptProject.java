import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
class encryptProject {
    public static void main(String[] args){
        System.out.println("Whats the name of the file");
        String file, filename;
        filename = Input.readString();
        file = Input.readFile(filename).toUpperCase();
        String build2 = file;
        String build = "";

        // List for random math operators
        ArrayList<String> operatorList = new ArrayList<String>(4);
        operatorList.add("multiply"); // position 0
        operatorList.add("divide"); // position 1
        operatorList.add("add"); // position 2
        operatorList.add("subtract"); // position 3


        // List for random integer from 1-10
        ArrayList<Integer> IntegerList = new ArrayList<Integer>(10);
        for(int i = 0; i <= 10; i++){
            IntegerList.add(i);
        }
        //List for the random generated operators used for decrypting
        ArrayList<String> RandomOperatorList = new ArrayList<String>(file.length()/2);

        //List for the random generated integers used for decrypting
        ArrayList<Integer> RandomIntegerList = new ArrayList<Integer>(file.length()/2);

        String randomEncrypt = "";

         for(int i = 0; i < file.length()/2; i++){
            int positionForward = (file.length()/2) + i;
            int positionBackward = (file.length()/2) - i;

            char symbolForward = file.charAt(positionForward);
            char symbolBackward = file.charAt(positionBackward);

            int asciiForward = ((int)symbolForward);
            int asciiBackward = ((int)symbolBackward);

            int randomOperator = (int)(Math.random()*4);
            int randomInteger = (int)(Math.random()*9)+1;

            RandomOperatorList.add(operatorList.get(randomOperator));
            RandomIntegerList.add(IntegerList.get(randomInteger));

            if(operatorList.get(randomOperator) == "multiply"){
                asciiForward = asciiForward * randomInteger;
                asciiBackward = asciiBackward * randomInteger;
            }
            else if(operatorList.get(randomOperator) == "divide"){
                asciiForward = (asciiForward) / randomInteger;
                asciiBackward = (asciiBackward) / randomInteger;
            }
            else if(operatorList.get(randomOperator) == "add"){
                asciiForward = asciiForward + randomInteger;
                asciiBackward = asciiBackward + randomInteger;
            }
            else if(operatorList.get(randomOperator) == "subtract"){
                asciiForward = asciiForward - randomInteger;
                asciiBackward = asciiBackward - randomInteger;
            }


            //Keeps the FORWARD alternation within A - Z
            if (asciiForward > (int)'Z'){
                while(asciiForward > (int)'Z'){
                    asciiForward =  (char)(asciiForward - 26);
                }
            }else if(asciiForward < (int)'A'){
                while(asciiForward < (int)'A'){
                    asciiForward = (char)(asciiForward + 26);
                }
            }

            //Keeps the BACKWARD alternation within A - Z
            if (asciiBackward > (int)'Z'){
                while(asciiBackward > (int)'Z'){
                    asciiBackward = (char)(asciiBackward - 26);
                }
            }else if(asciiBackward < (int)'A'){
                while(asciiBackward < (int)'A'){
                    asciiBackward = (char)(asciiBackward + 26);
                }
            }
            build += (char)asciiBackward;
            build += (char)asciiForward;
            randomEncrypt += RandomOperatorList.get(i).substring(0,1).toUpperCase();
            randomEncrypt += RandomIntegerList.get(i);
         }

        String build1 = "";
        String before, after;

       for(int i = 0; i < build.length(); i++){
           if(build.substring(i,i++).equals("a")){
                int pos = build.indexOf("a");
                before = build.substring(0,pos);
                after = build.substring(pos,build.length());
                build1 = before + "b" + after;
            }
       }
       for(int i= 0; i < build.length(); i++){
           char symbol = build.charAt(i);
           int ascii = ((int)symbol) + 10;                                                                                                                                                                                                                                                                                                                                                                                      
           if(ascii >(int)'B' && ascii <(int)'Z'){
               build1 +=(char)(ascii - 8);
           }else{
               build1 +=(char)(ascii);
           }
       }

       try{
            Files.write(Paths.get("Encrypted.txt"), build1.getBytes());
            Files.write(Paths.get("Encryptedd.txt"), build2.getBytes());
            }catch(Exception e){
        }
        try{
            Files.write(Paths.get("random.txt"), randomEncrypt.getBytes());
            }catch(Exception e){
        }
        System.out.println("\nOriginal: " + file);
        System.out.println("\nEncrypted: " + build);
        System.out.println("\nEncrypted second time: " + build1);
    }
}