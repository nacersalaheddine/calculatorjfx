package main.java.com.byteshift.calculatorjfx.about;

public enum WebLinks {
    FACEBOOK_LINK("https://www.facebook.com/byte.shift.tech"),
    DRIBBLE_LINK("https://dribbble.com/nacersalaheddine"),
    LINKEDIN_LINK("https://www.linkedin.com/in/nacersalaheddine/"),
    REPO_GITHUB_LINK("https://www.github.com/nacersalaheddine/calculatorjfx"),
    GITHUB_LINK("https://www.github.com/nacersalaheddine"),
    PERSONAL_SITE("https://www.facebook.com/byte.shift.tech");

    private final String key;

    WebLinks(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
