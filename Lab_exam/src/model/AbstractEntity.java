package model;

public class AbstractEntity {
    private Long id;

    public AbstractEntity(){

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
