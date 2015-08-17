package karstenroethig.maven.example;

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;

public class AppParams {

    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(
        names = {"-log", "-verbose" },
        description = "Level of verbosity"
    )
    private Integer verbose = 1;

    @Parameter(
        names = "-groups",
        description = "Comma-separated list of group names to be run",
        required = true
    )
    private String groups;

    @Parameter(
        names = "-debug",
        description = "Debug mode"
    )
    private boolean debug = false;

    @Parameter(
        names = {"-p", "--password" },
        description = "Connection password",
        password = true
    )
    private String password;

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public Integer getVerbose() {
		return verbose;
	}

	public void setVerbose(Integer verbose) {
		this.verbose = verbose;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
