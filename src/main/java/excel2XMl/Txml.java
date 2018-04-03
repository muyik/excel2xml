package excel2XMl;



import java.io.Serializable;
public class Txml implements Serializable{

	Integer version;
	String summary;
	String preconditions;
	Integer importance;
	String caseName;
	String steps;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		if (version==null){
			version=0;
		}
		this.version = version;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		if (summary==null){
			summary="";
		}
		this.summary = summary;
	}

	public String getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(String preconditions) {
		if (preconditions==null){
			preconditions="";
		}
		this.preconditions = preconditions;
	}

	public Integer getImportance() {
		return importance;
	}

	public void setImportance(Integer importance) {
		if (importance==null){
			importance=2;
		}
		this.importance = importance;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}
}
