package dao;

import com.sun.istack.internal.Nullable;
import model.MilkProduct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MilkProductDAO {
    private List<MilkProduct> milkProducts = new ArrayList<MilkProduct>();
    public MilkProductDAO() {
        MilkProduct tonedAavin = new MilkProduct("1", "toned", "aavin", 10.00);
        MilkProduct pasteurizedAavin = new MilkProduct("2", "pasteurized", "aavin", 12.00);
        MilkProduct tonedAarokya = new MilkProduct("3", "toned", "Aarokya", 11.00);
        MilkProduct pasteurizedAarokya = new MilkProduct("4", "pasteurized", "Aarokya", 14.00);
        MilkProduct tonedNandhini = new MilkProduct("5", "toned", "Nandhini", 9.00);
        MilkProduct pasteurizedNandhini = new MilkProduct("6", "pasteurized", "Nandhini", 13.00);
        milkProducts.addAll(Arrays.asList(tonedAavin, pasteurizedAavin, tonedAarokya, pasteurizedAarokya, tonedNandhini, pasteurizedNandhini));
    }

    public List<MilkProduct> getMilkProducts(String sortBy) {
        if(sortBy == null)
            return milkProducts;
        else if (sortBy.equals("price")) {
            ArrayList<MilkProduct> milkProductsSorted = new ArrayList<>(this.milkProducts);
            milkProductsSorted.sort(Comparator.comparing(MilkProduct::getIndividualPacketPrice));
            return milkProductsSorted;
        }
        else return Collections.emptyList();
    }


}
