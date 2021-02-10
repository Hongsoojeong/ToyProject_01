package Data;

public class ItemData {
    private String Date;
    private String Title;
    private String Number;

    public String getNumber() {
        return Number;
    }
    public void setNumber(String name) {
        this.Number = Number;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String image) {
        this.Title  = Title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String description) {
        this.Date = Date;
    }


    public ItemData(String name, String image, String description) {
        this.Number = name;
        this.Date = image;
        this.Title  = description;
    }
}
