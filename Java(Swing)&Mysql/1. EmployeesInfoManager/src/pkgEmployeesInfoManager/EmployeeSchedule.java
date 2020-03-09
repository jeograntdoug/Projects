/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgEmployeesInfoManager;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 1898918
 */
class InvalidValueException extends Exception {
  InvalidValueException(String msg) { super(msg); }
}


class EmployeeSchedule {
    private String name; // 2-50 characters, not permitted are: ;^?@!~*
    private boolean isManager;
    private String department; // 2-50 characters, not permitted are: ;^?@!~*
    private LocalDate dateHired; // year between 1900 and 2100
    private ArrayList<Weekday> workdaysList = new ArrayList<>(); // no duplicates allowed

    public static DateTimeFormatter dateFormatForFile = DateTimeFormatter.ofPattern("yyyy-M-d");
    public static DateTimeFormatter dateFormatForDisplay = DateTimeFormatter.ofPattern("MMM d yyyy");
    
    public static Map<Weekday,Integer> statsMap = new HashMap<>();

    public EmployeeSchedule(String name, boolean isManager, String department, Date dateHired) 
            throws InvalidValueException 
    {
        setName(name);
        setIsManager(isManager);
        setDepartment(department);
        setDateHired(dateHired);
    }

    public EmployeeSchedule(String dataLine) throws InvalidValueException 
    {
        String[] data = dataLine.split(";");

        if(data.length != 4){
            throw new InvalidValueException("Wrong Data Format: " + dataLine);
        }

        name = data[0];
        if(name.charAt(name.length()-1) == '*'){
            setIsManager(true);
            name = name.substring(0, name.length()-1);
        } else{
            setIsManager(false);
        }
        
        setName(name);
        setDateHired(data[1]);
        setDepartment(data[2]);
        
        if(data[3] == null){
            return;// nothing todo;
        }
        for(String str : data[3].split(",")){
            addWorkday(str);
        }
    }

    public static void initStatsMap(){
        for(Weekday w : Weekday.values()){
            statsMap.put(w, 0);
        }
    }
    
    public String getName(){ return name; }
    public void setName(String name)
      throws InvalidValueException{
        if(name == null){
            throw new InvalidValueException("Name is Null!");
        }

        if(!name.matches("[^;\\?@!~*^]{2,50}")){
            throw new InvalidValueException("Name Must be 2-50 characters, not permitted are: ;^?@!~* ");
        }

        this.name = name;
    }

    public boolean getIsManager(){ return isManager; }
    public void setIsManager(boolean isManager){ this.isManager = isManager; }
    public void setIsManager(String isManager){ setIsManager(Boolean.valueOf(isManager)); }

    public String getDepartment(){ return department;}
    public void setDepartment(String department)
      throws InvalidValueException{
        if(department == null){
            throw new InvalidValueException("Department is Null!");
        }

        if(!department.matches("[^;\\?@!~*^]{2,50}")){
            throw new InvalidValueException("Department Must be 2-50 characters, not permitted are: ;^?@!~* ");
        }

        this.department = department;
    }

    public LocalDate getDateHired(){ return dateHired; }
    public void setDateHired(LocalDate dateHired)
      throws InvalidValueException{
        int year = dateHired.getYear();

        if( year < 1900 || 2100 < year){
            throw new InvalidValueException("Year Must be between 1900 and 2100");
        }
        this.dateHired = dateHired;
    }
    public void setDateHired(Date dateHired) throws InvalidValueException{
        ZoneId zone = ZoneId.systemDefault();
        setDateHired(LocalDate.ofInstant(
                dateHired.toInstant(),
                zone
        ));
    }
    public void setDateHired(String dateHired) throws InvalidValueException{
        setDateHired(LocalDate.parse(dateHired,dateFormatForFile));
    }

    /**
     * It adds weekday to workdaysList but only if it is not on the list yet; 
     * if it is on the list it throws InvalidValueException
     */
    private void addWorkday(Weekday weekday) throws InvalidValueException {
        if(workdaysList.contains(weekday)){
            throw new InvalidValueException("Weekday is already list:" + weekday.toString());
        }
        statsMap.put(weekday, statsMap.get(weekday)+1);
        workdaysList.add(weekday);
    }
    private void addWorkday(String weekday) throws InvalidValueException {
        try{
            addWorkday(Weekday.valueOf(weekday));
        } catch (IllegalArgumentException ex){
            throw new InvalidValueException("Invalid Weekday: "+ weekday);
            
        }
    }

    public Weekday[] getWorkdays() { 
        Weekday[] arr = new Weekday [workdaysList.size()];

        return workdaysList.toArray(arr); 
    }
    void setWorkdays(Weekday [] workdaysArray) throws InvalidValueException {
          workdaysList.clear();
          for(Weekday w : workdaysArray){
              addWorkday(w);
          }
      }
    

    public boolean isWorkingOn(Weekday weekday) { return workdaysList.contains(weekday); }
    
    public String toDataString(){
        ArrayList<String> workdays = new ArrayList<>();
        
        for(Weekday w : workdaysList){
            workdays.add(w.toString());
        }
        
        return String.format(
                "%s%s;%s;%s;%s",
                name,
                isManager?"*":"",
                dateFormatForFile.format(dateHired),
                department,
                String.join(",", workdays)
        );
    }
    
    @Override
    public String toString(){
        ArrayList<String> workdays = new ArrayList<>();
        
        for(Weekday w : workdaysList){
            workdays.add(w.toString());
        }
        String strWeekday = "";
        if(workdays.size() == 0){
            strWeekday = "No schedule yet";
        } else{
            strWeekday = String.join(",", workdays);
        }
        
        return String.format("%s,%s of %s, hired %s.",
                name,
                isManager? "manager":"employee",
                dateFormatForDisplay.format(dateHired),
                strWeekday
        );
        
    }
    
    public static Comparator<EmployeeSchedule> compareByName = new Comparator<EmployeeSchedule>() {
        @Override
        public int compare(EmployeeSchedule e1, EmployeeSchedule e2){
            return e1.getName().compareTo(e2.getName());
        }
    };
    
    public static Comparator<EmployeeSchedule> compareByDateHire = new Comparator<EmployeeSchedule>() {
        @Override
        public int compare(EmployeeSchedule e1, EmployeeSchedule e2){
            return e1.getDateHired().compareTo(e2.getDateHired());
        }
    };
}