package Model;

public enum POSTTYPE {
    TEACH,
    LEARN,
    ALL;
    public String toString(){
        if(this == TEACH) return "teach";
        if(this == LEARN) return "learn";
        return "all";
    }

    public static POSTTYPE toPostType(String s){
        if(s.equals("teach")) return TEACH;
        if(s.equals("learn")) return LEARN;
        return ALL;
    }
}
