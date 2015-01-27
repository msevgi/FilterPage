
package tr.com.dteknoloji.filterpage;

import java.io.Serializable;

/**
 * Created by MustafaS on 27.1.2015.
 */
public class FilterModel implements Serializable {
   public final static int SORT_DIST = 0;
   public final static int SORT_ALP  = 1;
   public final static int SORT_POP  = 2;

   private int             sort;

   private int             distance;
   public final static int ALL       = 0;
   public final static int CHEAP     = 1;
   public final static int SUITABLE  = 2;
   public final static int EXPENSIVE = 3;
   private int             price;

   private String          kitchen;
   private String          consept;
   private String          place;
   private String          clothes;

   public FilterModel() {
   }

   public FilterModel(int sort, int distance, int price, String kitchen, String consept, String place, String clothes) {
      this.sort = sort;
      this.distance = distance;
      this.price = price;
      this.kitchen = kitchen;
      this.consept = consept;
      this.place = place;
      this.clothes = clothes;
   }

   public int getSort() {
      return sort;
   }

   public void setSort(int sort) {
      this.sort = sort;
   }

   public int getDistance() {
      return distance;
   }

   public void setDistance(int distance) {
      this.distance = distance;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public String getKitchen() {
      return kitchen;
   }

   public void setKitchen(String kitchen) {
      this.kitchen = kitchen;
   }

   public String getConsept() {
      return consept;
   }

   public void setConsept(String consept) {
      this.consept = consept;
   }

   public String getPlace() {
      return place;
   }

   public void setPlace(String place) {
      this.place = place;
   }

   public String getClothes() {
      return clothes;
   }

   public void setClothes(String clothes) {
      this.clothes = clothes;
   }
}
