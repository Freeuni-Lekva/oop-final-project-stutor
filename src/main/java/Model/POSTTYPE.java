package Model;

public enum POSTTYPE {
    TEACH,
    LEARN,
    BOTH;
    public String toString(){
        if(this == TEACH) return "teach";
        if(this == BOTH) return "both";
        return "learn";
    }

    public static POSTTYPE toPostType(String s){
        if(s.equals("teach")) return TEACH;
        if(s.equals("both")) return BOTH;
        return LEARN;
    }
}
