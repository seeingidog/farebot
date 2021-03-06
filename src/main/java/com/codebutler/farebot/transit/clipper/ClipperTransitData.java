/*
 * ClipperTransitData.java
 *
 * This file is part of FareBot.
 * Learn more at: https://codebutler.github.io/farebot/
 *
 * Copyright (C) 2014-2016 Eric Butler <eric@codebutler.com>
 *
 * Thanks to:
 * An anonymous contributor for reverse engineering Clipper data and providing
 * most of the code here.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.codebutler.farebot.transit.clipper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codebutler.farebot.FareBotApplication;
import com.codebutler.farebot.R;
import com.codebutler.farebot.transit.Refill;
import com.codebutler.farebot.transit.Subscription;
import com.codebutler.farebot.transit.TransitData;
import com.codebutler.farebot.transit.Trip;
import com.codebutler.farebot.ui.ListItem;
import com.google.auto.value.AutoValue;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@AutoValue
public abstract class ClipperTransitData extends TransitData {

    @NonNull
    static ClipperTransitData create(
            @NonNull String serialNumber,
            @NonNull List<Trip> trips,
            @NonNull List<Refill> refills,
            short balance) {
        return new AutoValue_ClipperTransitData(serialNumber, trips, refills, balance);
    }

    @NonNull
    @Override
    public String getCardName() {
        return "Clipper";
    }

    @NonNull
    @Override
    public String getBalanceString() {
        return NumberFormat.getCurrencyInstance(Locale.US).format(getBalance() / 100.0);
    }

    @Nullable
    @Override
    public List<Subscription> getSubscriptions() {
        return null;
    }

    @Nullable
    @Override
    public List<ListItem> getInfo() {
        return null;
    }

    @NonNull
    public static String getAgencyName(int agency) {
        if (ClipperData.AGENCIES.containsKey(agency)) {
            return ClipperData.AGENCIES.get(agency);
        }
        return FareBotApplication.getInstance().getString(R.string.unknown_format, "0x" + Long.toString(agency, 16));
    }

    @NonNull
    public static String getShortAgencyName(int agency) {
        if (ClipperData.SHORT_AGENCIES.containsKey(agency)) {
            return ClipperData.SHORT_AGENCIES.get(agency);
        }
        return FareBotApplication.getInstance().getString(R.string.unknown_format, "0x" + Long.toString(agency, 16));
    }

    abstract short getBalance();
}
