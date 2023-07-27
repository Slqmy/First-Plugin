package net.slqmy.first_plugin.commands.minecraft;

import net.slqmy.first_plugin.types.AbstractCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public final class BuffCommand extends AbstractCommand {

	private final AttributeModifier modifier = new AttributeModifier(
					UUID.nameUUIDFromBytes("attack_damage_modifier".getBytes()),
					Attribute.GENERIC_ATTACK_DAMAGE.toString(), 10,
					AttributeModifier.Operation.ADD_NUMBER,
					EquipmentSlot.HAND
	);

	public BuffCommand() {
		super(
						"buff",
						"Increase an item's attack damage attribute!",
						"/buff",
						new Integer[]{0},
						new String[]{},
						"first_plugin.buff",
						true
		);
	}

	@Override
	public boolean execute(@NotNull final CommandSender sender, @NotNull final String @NotNull [] args) {
		final Player player = (Player) sender;

		final ItemStack playerHeldItem = player.getInventory().getItemInMainHand();

		final ItemMeta mainHandMeta = playerHeldItem.getItemMeta();

		if (playerHeldItem.getType() == Material.AIR || mainHandMeta == null) {
			player.sendMessage(ChatColor.RED + "You must be holding a valid item to run this command!");
			return true;
		}

		mainHandMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);

		playerHeldItem.setItemMeta(mainHandMeta);

		player.sendMessage(ChatColor.LIGHT_PURPLE + "Item buffed! " + ChatColor.AQUA + "Have fun!");
		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
		return null;
	}
}
