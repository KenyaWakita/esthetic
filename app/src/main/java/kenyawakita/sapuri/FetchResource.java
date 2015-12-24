package kenyawakita.sapuri;


public class FetchResource {
    private String category;
    private String question;
    private String answer;
    private String hint;
    private String index;

    public FetchResource(String category, String question, String answer ,String hint, String index) {
        this.category = category;
        this.question = question;
        this.answer = answer;
        this.hint = hint;
        this.index = index;
    }


    public String getCategory(){
        return this.category;
    }
    public void setCategory(String c){
        this.category=c;
    }

    public String getQuestion(){
        return this.question;
    }
    public void setQuestion(String q){
        this.question=q;
    }

    public String getAnswer(){
        return this.answer;
    }
    public void setAnswer(String a){
        this.answer=a;
    }

    public String getHint(){
        return this.hint;
    }
    public void setHint(String h){
        this.hint=h;
    }

    public String getIndex(){
        return this.index;
    }
    public void setIndex(String i){
        this.index=i;
    }

}
