import java.util.ArrayList;

public class OperatorDTO {
	private int oprId;
	private String oprNavn;
	private String ini;
	private int cpr;
	private String password;
	private ArrayList<Integer> Users = new ArrayList<Integer>();

	{

		for (int i = 11; i <= 99; i++) {
			Users.add(i);
		}
	}

	private int userCounter = 0;

	public OperatorDTO(String oprNavn, String ini, int cpr, String password) {
		this.oprId = Users.get(userCounter);
		userCounter++;
		this.oprNavn = oprNavn;
		this.ini = generateIni(oprNavn);
		this.cpr = cpr;
		this.password = password;
	}

	public String getOprNavn(int oprId) {
		return oprNavn;
	}

	public void setOprNavn(int oprId, String navn) {
		this.oprNavn = navn;
	}

	public String getIni(int oprId) {
		return ini;
	}

	public int getCpr(int oprId) {
		return cpr;
	}

	public String getPassword(int oprId) {
		return password;
	}

	public void setPassword(int oprId, String newPw) {
		this.password = newPw;
	}
	
	public String generateIni(String name){
		String out;
		out = name.substring(0 ,1);
		int space = name.indexOf(32);
		out = (out + name.substring(space+1, space+2));
		return out;
	}
}
