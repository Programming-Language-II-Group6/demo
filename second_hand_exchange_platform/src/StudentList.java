import java.util.ArrayList;
public class StudentList {
	private ArrayList<Student> studentList;
	public StudentList() {
		this.studentList = new ArrayList<Student>();
	}
	public void addList(Student s) {
		this.studentList.add(s);
		
	}
	public Student getStudent(int stuID) {
		for(Student s:studentList) {
			if(s.getStudentID()==stuID) {
				return s;
			}
		}
		return null;
	}
	public Student getStudent(String name) {
		for(Student s:studentList) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
}
