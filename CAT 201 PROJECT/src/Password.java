import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Password {

    public boolean Pa(String content,String content2) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("root", "123456");
        String i=content.replace(" ","");
        String j=content2.replace(" ","");
        return j.equals(map.get(i));
    }
}
class FileSave{

    public static void save() throws IOException {
        File f=new File("employee.txt");
        if(f.exists()) {
            f.delete();
        }
        f.createNewFile();
        FileWriter fw=null;
        fw=new FileWriter("employee.txt");
        int i;
        for(i=0;i<chief.data.size();i++) {
            String tmp=new String(chief.data.get(i).getId()+"\t"+chief.data.get(i).getName()+"\t"+chief.data.get(i).getPosition()+"\t"+chief.data.get(i).getContact()+"\t"+chief.data.get(i).getWage()+"\t"+chief.data.get(i).getAllowances()+"\t"+chief.data.get(i).getSalary());
            fw.write(tmp+"\r\n");
        }
        fw.close();
    }
}

class FileRead {

    public static void read() throws IOException {
        FileReader fr=null;
        int i,j;
        fr=new FileReader("employee.txt");
        BufferedReader br=new BufferedReader(fr);
        String line=br.readLine();
        while(line!=null) {
            if(line.equals("")) {
                line=br.readLine();
                continue;
            }
            Employee s=new Employee();
            String tmp[]=line.split("\\s");
            for(j=0;j<tmp.length;j++) {
                switch(j) {
                    case 0:
                        s.setId(tmp[j]);
                        break;
                    case 1:
                        s.setName(tmp[j]);
                        break;
                    case 2:
                        s.setPosition(tmp[j]);
                        break;
                    case 3:
                        s.setContact(tmp[j]);
                        break;
                    case 4:
                        s.setWage(tmp[j]);
                        break;
                    case 5:
                        s.setAllowances(tmp[j]);
                        break;
                    case 6:
                        s.setSalary(tmp[j]);
                        break;
                }
            }
            boolean flag=true;
            for(i=0;i<chief.data.size();i++) {
                if(s.getId().equals(chief.data.get(i).getId())) {
                    flag=false;
                }
            }
            if(flag) {
                chief.data.add(s);
            }
            line=br.readLine();
        }
        fr.close();
    }

}
class Employee {

    private String id;
    private String name;
    private String position;
    private String contact;
    private String wage;
    private String allowances;
    private String salary;

    public Employee() {
    }

    public Employee(String id,String name,String position,String contact,String wage,String allowances,String salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.contact = contact;
        this.wage = wage;
        this.allowances = allowances;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getWage() {
        return wage;
    }
    public void setWage(String wage) {
        this.wage = wage;
    }

    public String getAllowances() {
        return allowances;
    }
    public void setAllowances(String allowances) {
        this.allowances = allowances;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

}