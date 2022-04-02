package com.careerdevs.NewResources.Models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/*"id": 1432,
    "user_id": 2937,
    "title": "Tergum ascit ea carbo distinctio asperiores vulgo compono velit ait vos concido pariatur accedo tabesco.",
    "body": "Calco aut audax. Tibi debeo hic.
     Denuncio adeptio pecus. Verumtamen eligendi pecco.
     Certo voluptatem vae. Vulnero acer eum.
     Sponte conduco thesaurus. Coadunatio aut deficio.
     Sperno verus arbitro. Synagoga hic angustus.
      Curso textor curtus. Theca doloremque sortitus. Vapulus congregatio vulgivagus. Alias qui via. Corpus suffragium velut. Adflicto eos vix. Bene aggredior dedico. Cicuta cubo vergo."
*/
public class PostModel {
    private int id;

/*The JsonProperty annotation is used
to map property names with JSON keys during serialization and deserialization.
By default, if you try to serialize a POJO,
 the generated JSON will have keys mapped to the fields of the POJO.
 */
    @JsonProperty("user_id")
    private int userId;
    private String title;
    private String body;

    public PostModel(){


    }

    public PostModel(int id, int userId,String title,String body){
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }


    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
