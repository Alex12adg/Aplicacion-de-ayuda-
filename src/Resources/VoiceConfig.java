package Resources;

public class VoiceConfig {

    private String keyword = "socorro";

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {

        if (keyword != null && !keyword.isEmpty()) {
            this.keyword = keyword.toLowerCase();
        }
    }
}