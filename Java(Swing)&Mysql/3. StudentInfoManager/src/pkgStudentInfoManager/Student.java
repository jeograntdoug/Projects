
package pkgStudentInfoManager;

public class Student {
    //Constructor for User Input
    public Student(int id,String name,String gradesList) throws InvalidDataException{
        setId(id);
        setName(name);
        setGradesListFromStr(gradesList);
        setCard(null);
    }
    //Constructor for data from database
    public Student(int id,String name,String[] gradesList,Card card) throws InvalidDataException{
        setId(id);
        setName(name);
        setGradesList(gradesList);
        setCard(card);
    }
    
    private int id;
    private String name;
    private String[] gradesList;
    private double gpa;
    private Card card;
    
    public int getId() {return this.id; }
    private void setId(int id) { this.id = id; }
    
    public String getName() {return this.name; }
    private void setName(String name) throws InvalidDataException {
        if(name == null){
            throw new InvalidDataException("Name is empty");
        }
        if(name.length() > 100 || name.length() < 2){
            throw new InvalidDataException("Name must be 2~100 chars. \"" + name + "\"given");
        }
        this.name = name;
    }
    
    public String[] getGradesList(){return this.gradesList; }
    public String getGradesListStr() { 
        if(gradesList == null){
            return "";
        }
        return String.join(",", gradesList); 
    }
    private void setGradesListFromStr(String gradesList) throws InvalidDataException{
        if(gradesList == null){
            setGradesList(null);
            return;
        }
        if(gradesList.isEmpty()){
            setGradesList(null);
            return;
        }
        setGradesList(gradesList.trim().split("[ ,\n\t]+"));
    }
    private void setGradesList(String[] gradesList) throws InvalidDataException{         
        if(gradesList == null){
            this.gradesList = null;
            this.gpa = 0.0;
            return;
        }
        if(gradesList.length == 0){
            this.gradesList = null;
            this.gpa = 0.0;
            return;
        }
        this.gradesList = gradesList;
        this.gpa = calculateGpa(gradesList);
    }
    
    public Card getCard(){ return this.card; }
    private void setCard(Card card){ this.card = card; }
    
    public double getGpa(){return gpa;}
    
    private double calculateGpa(String[] grades) throws InvalidDataException{
        double totalGrade = 0;
        for(String grade : grades){
            switch(grade){
                case "A+":
                    totalGrade += 4.3;
                    break;
                case "A":
                    totalGrade += 4.0;
                    break;
                case "A-":
                    totalGrade += 3.7;
                    break;
                case "B+":
                    totalGrade += 3.3;
                    break;
                case "B":
                    totalGrade += 3.0;
                    break;
                case "B-":
                    totalGrade += 2.7;
                    break;
                case "C+":
                    totalGrade += 2.3;
                    break;
                case "C":
                    totalGrade += 2.0;
                    break;
                case "C-":
                    totalGrade += 1.7;
                    break;
                case "D":
                    totalGrade += 1.0;
                    break;
                case "F":
                    totalGrade += 0.0;
                    break;
                default:
                        throw new InvalidDataException("Invalid grade. \"" + grade + "\"given");    
            }
        }
        return totalGrade/grades.length;
    }
    
    @Override
    public String toString(){
        return String.format(
                "%d:%s(%s) GPA=%s"
                ,id,name,
                (card == null)?"no card" :card.getPermCode(),
                (gradesList == null) ? "none" : String.format("%.2f", gpa)
        );
    }
}
