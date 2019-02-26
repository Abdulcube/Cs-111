public class StringRec {

public static String decompress(String compressedText)
{
String finalString = "";
char x;
if(compressedText.length()<=1)
return compressedText;
x = compressedText.charAt(0);
if(Character.isDigit(x))
{
if(x!='0')
{
x--;
finalString = finalString + compressedText.substring(1,2) + decompress(x + compressedText.substring(1));
}
else
finalString = finalString + decompress(compressedText.substring(2));
}
else
finalString = finalString + compressedText.substring(0,1) + decompress(compressedText.substring(1));
return finalString;
}
}

