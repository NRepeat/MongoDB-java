import java.io.Serializable;

class Check implements Serializable {

    private static final long serialVersionUID = 1L;
    private String check_date = null;
    private String[] price = null;
    private Integer check_id=0;

    Check() {
    }

    public Integer getCheck_id(){return check_id;}
    public void setCheck_id(Integer check_id){this.check_id = check_id;}
    public String getCheck_date(){return check_date;}
    public void setCheck_date(String check_date){this.check_date= check_date;}
    public String[] getPrice(){return price;}
    public void setPrice(String[] price){this.price=price;}


    public Check(String check_date, Integer check_id, String[] price){
    }}
