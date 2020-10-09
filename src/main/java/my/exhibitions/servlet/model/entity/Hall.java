package my.exhibitions.servlet.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Hall {

    private Long id;

    private String nameEnglish;

    private String nameUkrainian;

    private String descriptionEnglish;

    private String descriptionUkrainian;

    private String imageUrl;

    private List<ExhibitionEvent> exhibitionEvents = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public String getNameUkrainian() {
        return nameUkrainian;
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

    public List<ExhibitionEvent> getExhibitionEvents() {
        return exhibitionEvents;
    }

    public static class Builder{
        private final Hall hall;

        public Builder() {
            hall = new Hall();
        }

        public Builder id(Long id){
            hall.id = id;
            return this;
        }

        public Builder nameEnglish(String nameEnglish){
            hall.nameEnglish = nameEnglish;
            return this;
        }

        public Builder nameUkrainian(String nameUkrainian){
            hall.nameUkrainian = nameUkrainian;
            return this;
        }

        public Builder descriptionEnglish(String  descriptionEnglish){
            hall.descriptionEnglish = descriptionEnglish;
            return this;
        }

        public Builder descriptionUkrainian(String descriptionUkrainian){
            hall.descriptionUkrainian = descriptionUkrainian;
            return this;
        }

        public Builder exhibitionEvents(List<ExhibitionEvent> exhibitionEvents){
            hall.exhibitionEvents = exhibitionEvents;
            return this;
        }

        public Builder imageUrl(String imageUrl){
            hall.imageUrl = imageUrl;
            return this;
        }

        public Hall build() {
            return hall;
        }
    }
}
