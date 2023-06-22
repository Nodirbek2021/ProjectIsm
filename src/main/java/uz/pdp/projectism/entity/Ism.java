package uz.pdp.projectism.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.projectism.entity.template.AbsEntity;
import uz.pdp.projectism.entity.template.AbsEntityLong;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ism")
public class Ism extends AbsEntityLong {
    @Column(name = "name_lat",columnDefinition = "text")
    private String nameLat;
    @Column(name = "name_cry",columnDefinition = "text")
    private String nameCyr;
    @Column(name = "gender")
    private boolean gender;
    @Column(name = "definition", columnDefinition = "text")
    private String definition;
    @Column(name = "named_people", columnDefinition = "text")
    private String namedPeople ;
    @Column(name = "like_count")
    private Integer likeCount ;
    @Column(name = "coming_lang", columnDefinition = "text")
    private String comingLang;

    public Ism(Timestamp updatedAt) {
        super(updatedAt);
    }
}
