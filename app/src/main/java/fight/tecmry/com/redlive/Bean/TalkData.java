package fight.tecmry.com.redlive.Bean;

/**
 * Created by Tecmry on 2017/8/20.
 */

public class TalkData {

    private String Name;
    private String text;
    private int Type;
    private String FileAdress;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setFileAdress(String fileAdress) {
        FileAdress = fileAdress;
    }

    public String getFileAdress() {
        return FileAdress;
    }
}
