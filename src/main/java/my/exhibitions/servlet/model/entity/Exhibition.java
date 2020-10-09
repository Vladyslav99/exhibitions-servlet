package my.exhibitions.servlet.model.entity;

public class Exhibition {

    private Long id;

    private String themeEnglish;

    private String themeUkrainian;

    private String descriptionEnglish;

    private String descriptionUkrainian;

    private String imageUrl;

    public Long getId() {
        return id;
    }

    public String getThemeEnglish() {
        return themeEnglish;
    }

    public String getThemeUkrainian() {
        return themeUkrainian;
    }

    public String getDescriptionEnglish() {
        return descriptionEnglish;
    }

    public String getDescriptionUkrainian() {
        return descriptionUkrainian;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public static class Builder{
        private final Exhibition exhibition;

        public Builder() {
            exhibition = new Exhibition();
        }

        public Builder id(Long id){
            exhibition.id = id;
            return this;
        }

        public Builder themeEnglish(String themeEnglish){
            exhibition.themeEnglish = themeEnglish;
            return this;
        }

        public Builder themeUkrainian(String themeUkrainian){
            exhibition.themeUkrainian = themeUkrainian;
            return this;
        }

        public Builder descriptionEnglish(String  descriptionEnglish){
            exhibition.descriptionEnglish = descriptionEnglish;
            return this;
        }

        public Builder descriptionUkrainian(String descriptionUkrainian){
            exhibition.descriptionUkrainian = descriptionUkrainian;
            return this;
        }

        public Builder imageUrl(String imageUrl){
            exhibition.imageUrl = imageUrl;
            return this;
        }

        public Exhibition build() {
            return exhibition;
        }
    }
}
