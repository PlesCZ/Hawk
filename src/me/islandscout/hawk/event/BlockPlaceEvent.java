/*
 * This file is part of Hawk Anticheat.
 *
 * Hawk Anticheat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Hawk Anticheat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Hawk Anticheat.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.islandscout.hawk.event;

import me.islandscout.hawk.HawkPlayer;
import me.islandscout.hawk.util.packet.WrappedPacket;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class BlockPlaceEvent extends Event {

    //WARNING: Also gets called when interacting with blocks.

    private final Location location;
    private final Material material;
    private final BlockFace blockFace;

    public BlockPlaceEvent(Player p, HawkPlayer pp, Location location, Material material, BlockFace blockFace, WrappedPacket packet) {
        super(p, pp, packet);
        this.location = location;
        this.material = material;
        this.blockFace = blockFace;
    }

    public Location getLocation() {
        return location;
    }

    public Location getTargetedBlockLocation() {
        switch (blockFace) {
            case TOP:
                return new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ());
            case EAST:
                return new Location(location.getWorld(), location.getX() - 1, location.getY(), location.getZ());
            case WEST:
                return new Location(location.getWorld(), location.getX() + 1, location.getY(), location.getZ());
            case NORTH:
                return new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() + 1);
            case SOUTH:
                return new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() - 1);
            case BOTTOM:
                return new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ());
        }
        return null;
    }

    public Material getMaterial() {
        return material;
    }

    public Vector getTargetedBlockFaceNormal() {
        switch (blockFace) {
            case TOP:
                return new Vector(0, 1, 0);
            case BOTTOM:
                return new Vector(0, -1, 0);
            case SOUTH:
                return new Vector(0, 0, 1);
            case NORTH:
                return new Vector(0, 0, -1);
            case WEST:
                return new Vector(-1, 0, 0);
            case EAST:
                return new Vector(1, 0, 0);
            default:
                return null;
        }
    }

    public BlockFace getTargetedBlockFace() {
        return blockFace;
    }

    public enum BlockFace {
        NORTH, SOUTH, EAST, WEST, TOP, BOTTOM
    }
}