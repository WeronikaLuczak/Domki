package pl.wluczak.projektdomki.service;

import org.springframework.stereotype.Service;
import pl.wluczak.projektdomki.utils.DateUtils;

import java.util.Date;

@Service
public class DiscountService {

    private static final Date START_SEASON_DATE = DateUtils.convertDate("2023-06-01");
    private static final Date END_SEASON_DATE = DateUtils.convertDate("2023-08-31");
// from 05.05 to 08.08

    public double countDiscountPrice(Date from, Date to, double pricePerDayInSeason, double pricePerDayOutOfSeason) {
        double price = 0;

        if (from.before(START_SEASON_DATE)) {
            int daysBeforeSeason = DateUtils.countDifferenceDaysBetween(from, DateUtils.min(START_SEASON_DATE, to));
            double priceBeforeSeason = daysBeforeSeason * pricePerDayOutOfSeason;
            price = priceBeforeSeason;
        }
        if (to.after(START_SEASON_DATE)) {
            int daysInSeason = DateUtils.countDifferenceDaysBetween(START_SEASON_DATE, DateUtils.min(END_SEASON_DATE, to));
            double seasonPrice = daysInSeason * pricePerDayInSeason;
            price = price + seasonPrice;
        }
        if (to.after(END_SEASON_DATE)) {
            int daysAfterSeason = DateUtils.countDifferenceDaysBetween(END_SEASON_DATE, to);
            double priceAfterSeason = daysAfterSeason * pricePerDayOutOfSeason;
            price = priceAfterSeason + price;
        }
        return price;

    }
}
