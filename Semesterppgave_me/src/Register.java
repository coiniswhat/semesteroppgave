import java.io.Serializable;                 
 
public class Register implements Serializable {
 
    public String language;
    public String encoding;
    public String fontSize;
    public String fontFamily;
    public boolean autoSave;
    public boolean autoComplete;
    public transient String direction;        
 
}