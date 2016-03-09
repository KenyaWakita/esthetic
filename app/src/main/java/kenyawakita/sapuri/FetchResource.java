package kenyawakita.sapuri;


public class FetchResource {
    private String category;
    private String question_No;
    private String question_No_solved;
    private String question;
    private String answer;
    private String description;
    private String hint;
    private String index;

    public FetchResource(String category, String question_No, String question_No_solved,String question, String answer ,
                         String description, String hint, String index) {

        this.category = category;
        this.question_No = question_No;
        this.question_No_solved = question_No_solved;
        this.question = question;
        this.answer = answer;
        this.description = description;
        this.hint = hint;
        this.index = index;
    }



    public String getCategory(){
        return this.category;
    }
    public void setCategory(String c){
        this.category=c;
    }

    public String getQuestion_No(){
        return this.question_No;
    }
    public void setQuestion_No(String q_No){
        this.question_No=q_No;
    }

    public String getQuestion_No_solved(){
        return this.question_No_solved;
    }
    public void setQuestion_No_solved(String q_No_solved){
        this.question_No=q_No_solved;
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

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String d){
        this.description=d;
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
