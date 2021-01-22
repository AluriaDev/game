package io.github.aluria.game.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.server.v1_12_R1.NBTCompressedStreamTools;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.*;
import java.math.BigInteger;

public class GameSerializers {
  
  public static final ItemStack[] EMPTY_ITEM_STACK_ARRAY = new ItemStack[0];
  
  private static final Gson GSON = new GsonBuilder()
    .enableComplexMapKeySerialization()
    .serializeSpecialFloatingPointValues()
    .create();
  
  public static String toJson(Object object){
    return GSON.toJson(object);
  }
  
  public static <T> T fromJson(String json , Class<T> clazz){
    return GSON.fromJson(json , clazz);
  }
  
  public static String serializeLocation(Location location) {
    String worldName = location.getWorld().getName();
    double x = location.getX();
    double y = location.getY();
    double z = location.getZ();
    
    return worldName + ":" + x + ":" + y + ":" + z;
  }
  
  public static Location deserializeLocation(String rawString) {
    String[] split = rawString.split(":");
    
    World world = Bukkit.getWorld(split[0]);
    double x = Double.parseDouble(split[1]);
    double y = Double.parseDouble(split[2]);
    double z = Double.parseDouble(split[3]);
    
    return new Location(world, x, y, z);
  }
  
  public static String toBase64List(ItemStack[] items) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
    try (BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {
      
      // Content Size
      // Contents
      dataOutput.writeInt(items.length);
      
      int index = 0;
      for (ItemStack is : items) {
        if (is != null && is.getType() != Material.AIR) {
          dataOutput.writeObject(toBase64(is));
        } else {
          dataOutput.writeObject(null);
        }
        dataOutput.writeInt(index);
        index++;
      }
      
      return Base64Coder.encodeLines(outputStream.toByteArray());
    } catch (Exception e) {
      throw new IllegalStateException("Unable to save item stacks.", e);
    }
  }
  
  /**
   * ItemStack List from Base64
   */
  public static ItemStack[] fromBase64List(String items) {
    try {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(items));
      BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
      
      int size = dataInput.readInt();
      
      ItemStack[] list = new ItemStack[size];
      // Read the serialized inventory
      for (int i = 0; i < size; i++) {
        Object utf = dataInput.readObject();
        int slot = dataInput.readInt();
        if (utf == null) { // yey²?
        
        } else {
          list[slot] = fromBase64((String) utf);
        }
      }
      
      dataInput.close();
      return list;
    } catch (Exception e) {
      throw new IllegalStateException("Unable to load item stacks.", e);
    }
  }
  
  public static String toBase64(ItemStack item) {
    try {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      DataOutputStream dataOutput = new DataOutputStream(outputStream);
      
      NBTTagList nbtTagListItems = new NBTTagList();
      NBTTagCompound nbtTagCompoundItem = new NBTTagCompound();
      
      net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
      
      nmsItem.save(nbtTagCompoundItem);
      
      nbtTagListItems.add(nbtTagCompoundItem);
      
      NBTCompressedStreamTools.a(nbtTagCompoundItem, (DataOutput) dataOutput);
      
      return new BigInteger(1, outputStream.toByteArray()).toString(32);
    } catch (Exception e) {
      throw new IllegalStateException("Unable to serialize item stacks.", e);
    }
  }
  
  /**
   * Item from Base64
   *
   * @param data
   * @return
   */
  public static ItemStack fromBase64(String data) {
    try {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(new BigInteger(data, 32).toByteArray());
      
      NBTTagCompound nbtTagCompoundRoot = NBTCompressedStreamTools.a(new DataInputStream(inputStream));
      
      net.minecraft.server.v1_12_R1.ItemStack nmsItem = new net.minecraft.server.v1_12_R1.ItemStack(nbtTagCompoundRoot);
      ItemStack item = CraftItemStack.asBukkitCopy(nmsItem);
      
      return item;
    } catch (Exception e) {
      throw new IllegalStateException("Unable to serialize item stacks.", e);
    }
  }
}