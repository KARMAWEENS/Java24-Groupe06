package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.Promotion.IPromotion;
import org.movieTheatre.java24groupe06.models.tickets.Ticket;

import java.util.List;
/**
 * The PromotionManager class provides methods for managing promotions.
 */
public class PromotionManager  {
    double minPrice;
   private IPromotion[] promotions = new IPromotion[]{
            new FamilyPackPromotion(),
            new SchoolPackPromotion(),
            new GroupPackPromotion(),
            new HandicapPackPromotion()
    };
    private List<Ticket> tickets;
    /**
     * Initializes a new instance of the PromotionManager class.
     *
     * @param tickets the list of tickets.
     */
    public PromotionManager(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public double calculateBestPrice() {
         minPrice = Double.MAX_VALUE;
        calculateBestPriceRecursive(promotions, 0);
        return minPrice;
    }


    /**
     * Calculates the best price for the tickets.
     *
     * @param arr the array of promotions.
     * @param currentIndex the current index.
     */
    private void calculateBestPriceRecursive(IPromotion[] arr, int currentIndex) {
        if (currentIndex == arr.length - 1) {
            double priceForThisCombo = calculatePriceWithComboReduction(arr);
            if (priceForThisCombo < minPrice) {
                minPrice = priceForThisCombo;
            }
            return;
        }
        for (int i = currentIndex; i < arr.length; i++) {
            swap(arr, currentIndex, i);
            calculateBestPriceRecursive(arr, currentIndex + 1);
            swap(arr, currentIndex, i);
        }
    }
    /**
     * Calculates the price with the combo reduction.
     *
     * @param promotions the array of promotions.
     * @return the price with the combo reduction.
     */
    private double calculatePriceWithComboReduction(IPromotion[] promotions) {
        tickets.stream().forEach(ticket -> ticket.setPromotionApplied(false));
        double price = calculateTotalPrice();
        for (IPromotion promotion : promotions) {
            price -= promotion.calculateDiscount(tickets);
        }
        return price;
    }
    /**
     * Calculates the total price of the tickets.
     *
     * @return the total price of the tickets.
     */
    private double calculateTotalPrice() {
        return tickets.stream().mapToDouble(Ticket::getPrice).sum();
    }

    private void swap(IPromotion[] arr, int i, int j) {
        IPromotion temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
