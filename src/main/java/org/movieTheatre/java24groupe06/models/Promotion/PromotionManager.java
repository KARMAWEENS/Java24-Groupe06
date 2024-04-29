package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.Promotion.IPromotion;
import org.movieTheatre.java24groupe06.models.tickets.Ticket;

import java.util.List;

public class PromotionManager  {
    double minPrice;
   private IPromotion[] promotions = new IPromotion[]{
            new FamilyPackPromotion(),
            new SchoolPackPromotion(),
            new GroupPackPromotion(),
            new HandicapPackPromotion()
    };
    private List<Ticket> tickets;

    public PromotionManager(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public double calculateBestPrice() {

        System.out.println("PromotionM bestPrice" + this.tickets);
        double minPrice = Double.MAX_VALUE;
        calculateBestPriceRecursive(promotions, 0);
        return minPrice;
    }

    public double findBestPrice() {
        minPrice = Double.MAX_VALUE;
        calculateBestPrice();
        return minPrice;
    }
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

    private double calculatePriceWithComboReduction(IPromotion[] promotions) {
        tickets.stream().forEach(ticket -> ticket.setPromotionApplied(false));
        double price = calculateTotalPrice();
        for (IPromotion promotion : promotions) {
            price -= promotion.calculateDiscount(tickets);
        }
        return price;
    }

    private double calculateTotalPrice() {
        return tickets.stream().mapToDouble(Ticket::getPrice).sum();
    }

    private void swap(IPromotion[] arr, int i, int j) {
        IPromotion temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
