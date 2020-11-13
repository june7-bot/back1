package zen.zen.entity;

import lombok.*;

import javax.persistence.*;

import static zen.zen.entity.OrderStatus.ORDER;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "Dog.findOneList",
        query = "select d from Dog d where d.status = :status")
@NamedQuery(
        name = "Dog.findByOwner",
        query = "select d from Dog d where d.owner = :owner")
@NamedQuery(
        name = "Dog.findByOwnerOne",
        query = "select d from Dog d where d.owner = :owner and d.status = :status")
@NamedQuery(
        name = "Dog.findByOwnerZero",
        query = "select d from Dog d where d.owner = :owner and d.status = :status")
public class Dog {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private String photo;
    private Long owner;
    private String nose;
    private String birthFile;
    private String dogKind;
    private String dogGender;
    private String dogPrevent;
    private String dogSize;
    private int dogAge;

    @Enumerated(EnumType.STRING)
    @Setter
    private dogStatus status;


    public String checkSize(String size) {
        String big [] = {"골든 리트리버", "시베리안 허스키", "보더콜리", "사모예드" , "말라뮤트"};
        String mid [] = {"스피츠","시바견", "웰시코기" , "프렌치불독", "비글"};
        String small [] = {"비숑","포메라니안", "푸들" , "치와와", "닥스훈트", "말티즈", "시츄", "요크셔"};

        System.out.println("================================" + size);
        String dogSize = "";
        for ( String x : big
             ) {
            if(size == x) dogSize = "BIG";
        }
        for ( String x : mid
        ) {
            if(size == x) dogSize = "MID";
        }
        for ( String x : small
        ) {
            if(size == x) dogSize = "SMALL";
        }

      return dogSize;
    }

    public String checkSmallKind(int number) {
        switch(number){
            case 0:
                return "푸들";
            case 1:
                return "치와와";
            case 2 :
                return "닥스훈트";
            case 3 :
                return "비숑";
            case 4 :
                return "포메라니안";
            case 5 :
                return "말티즈";
            case 6 :
                return "시츄";
            case 7 :
                return "요크셔";
            default :
                return "";
        }
    }

    public String checkMidKind(int number) {
        switch (number) {
            case 0:
                return "스피츠";
            case 1:
                return "시바견";
            case 2:
                return "웰시코기";
            case 3:
                return "프렌치불독";
            case 4:
                return "비글";
            default:
                return "";
        }
    }

    public String checkBigKind(int number) {
        switch(number){
            case 0:
                return "골든 리트리버";
            case 1:
                return "시베리안 허스키";
            case 2 :
                return "보더콜리";
            case 3 :
                return "사모예드";
            case 4 :
                return "말라뮤트";
            default :
                return "";
        }
    }
}
