package dev.minealert.modules.alert;

import com.google.common.collect.Lists;
import dev.minealert.files.lang.Lang;
import dev.minealert.utils.MessageUtils;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class StaffAlert {

    private static StaffAlert instance = null;
    private List<String> staffAlertList = Collections.synchronizedList(Lists.newLinkedList());

    public static StaffAlert getInstance() {
        if (instance == null) instance = new StaffAlert();
        return instance;
    }

    public void toggleAlert(Player staffMember) {
        final String name = staffMember.getName();
        if (!containsStaffMember(name)) {
            addStaffMember(name);
            MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NOTIFY_ENABLED.toConfigString(), staffMember);
        } else {
            removeStaffMember(name);
            MessageUtils.sendFormattedMessage(Lang.PREFIX.toConfigString() + Lang.NOTIFY_DISABLED.toConfigString(), staffMember);
        }
    }

    private void addStaffMember(String name) {
        staffAlertList.add(name);
    }

    private void removeStaffMember(String name) {
        staffAlertList.remove(name);
    }

    public boolean containsStaffMember(String name) {
        return staffAlertList.contains(name);
    }

    public List<String> getStaffAlert() {
        return staffAlertList;
    }
}
