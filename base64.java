public class base64 {

    private final StringBuilder alphabet = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    private final String str = "hello world!!!";

    public String getbinCode(String message){
        StringBuilder sb = new StringBuilder(message);
        StringBuilder binCode = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            String str = Integer.toBinaryString(sb.charAt(i));
            int leng = str.length();
            while(leng<8){
                binCode.append("0");
                leng++;
            }
            binCode.append(str);
        }
       // System.out.println("binCode= "+binCode);
        return binCode.toString();
    }

  public String coder(String message){

        StringBuilder output = new StringBuilder();
        StringBuilder bin = new StringBuilder(getbinCode(message));

        int count = 0;
        StringBuilder sb =  new StringBuilder();
        sb.append("00");

        for (int i = 0; i < bin.length() ; i++) {

            sb.append(bin.charAt(i));
            count++;

            if(count==6){
                int key = Integer.parseInt(sb.toString(),2);
                //System.out.println("key"+key);
                char symb = alphabet.charAt(key);
               // System.out.println("symb "+symb);
                output.append(symb);

                sb = new StringBuilder();
                sb.append("00");
                count = 0;
            }
            else{
                if(i==bin.length()-1){
                    int k = 8 - sb.length();
                    for (int j = 0; j < k; j++) {
                        sb.append("0");
                    }
                    int key = Integer.parseInt(sb.toString(),2);
                    //System.out.println("key "+key);
                    char symb = alphabet.charAt(key);
                    //System.out.println("symb "+symb);
                    output.append(symb);

                }
            }
        }
        if(message.length()%3!=0){
            if (message.length()%3==1) {
                output.append("==");
            }
            if (message.length()%3==2) {
                output.append("=");
            }
        }
        //System.out.println("output : "+output.toString());
        return output.toString();
    }


    public String decoder(String input){
        StringBuilder sb = new StringBuilder(input);
        StringBuilder binCode = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i)=='='){
                continue;
            }
            String c = Character.toString(sb.charAt(i));
            int key = alphabet.indexOf(c);
            String binKey = Integer.toBinaryString(key);
            int k = 6 - binKey.length();
            for (int j = 0; j < k; j++) {
                binCode.append("0");
            }
            binCode.append(binKey);

        }
        //System.out.println("binCode: "+binCode);

        StringBuilder output = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        int count = 0;
        for (int i = 0; i < binCode.length() ; i++) {
            if(binCode.charAt(i)!=' ') {
                sb1.append(binCode.charAt(i));
                count++;
            }

                if(count==8){
                    int key = Integer.parseInt(sb1.toString(),2);
                   // System.out.println("key "+key);
                    String symb = Character.toString((char)key);
                   // System.out.println("symb "+symb);
                    output.append(symb);

                    sb1 = new StringBuilder();
                    count = 0;
                }
                else{
                    if(i==binCode.length()-1){
                        int k = 8 - sb1.length();
                        for (int j = 0; j < k; j++) {
                            sb1.append("0");
                        }
                        int key = Integer.parseInt(sb1.toString(),2);
                     //   System.out.println("key "+key);
                        String symb = Character.toString((char)key);
                     //   System.out.println("symb "+symb);
                        output.append(symb);

                    }
                }
           // System.out.println("output: "+output.toString());
        }
        return output.toString();
    }

    public static void main(String[] args) {
        base64 bs = new base64();
        String code = bs.coder(bs.str);
        System.out.println("\'"+bs.str+"\'"+" was coding into "+"\'"+code+"\'");
        String decode = bs.decoder(code);
        System.out.println("\'"+code+"\'"+" was decoding into "+"\'"+decode+"\'");
    }
}
