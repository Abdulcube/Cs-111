
public class Compress {
public static void main(String[] args){
	System.out.print("enter string: ");
	String phrase =IO.readString();
	String result=compress(phrase);
	IO.outputStringAnswer(result);
}
public static String compress (String original){
	char temporary=original.charAt(0);
	int currentLetter=0;
	String compressed="";
	String result="";
	for(int k=0; k<original.length(); k++) {
		char now=original.charAt(k);
		if (now==temporary) {
			currentLetter++;	
			temporary=now;
			if(k==original.length()-1) {
				if(currentLetter==1) {
					compressed=result+temporary;
					} else {
				compressed=result+currentLetter+temporary;
					}
				result=compressed;
			}
		}else {
			if(k==original.length()-1) {
				if(currentLetter==1) {
					compressed=result+temporary+now;
					} else {
			compressed=result+currentLetter+temporary+now;
					}
			}else {
				if(currentLetter==1) {
					compressed=result+temporary;
					} else {
				compressed=result+currentLetter+temporary;
					}
			}
			result=compressed;
			temporary=now;
			currentLetter=1;
		}
	}
	return compressed;
}
}

