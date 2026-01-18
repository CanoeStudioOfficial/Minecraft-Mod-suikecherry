package suike.suikecherry.sound.soundtype;

import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundTypeSuspiciousSand extends SoundType {
    public SoundTypeSuspiciousSand() {
        super(1.0F, 1.0F, 
            new SoundEvent(new ResourceLocation("suikecherry", "block.suspicious_sand.break")),
            new SoundEvent(new ResourceLocation("suikecherry", "block.suspicious_sand.step")),
            new SoundEvent(new ResourceLocation("suikecherry", "block.suspicious_sand.place")),
            new SoundEvent(new ResourceLocation("suikecherry", "block.suspicious_sand.hit")),
            new SoundEvent(new ResourceLocation("suikecherry", "block.suspicious_sand.fall"))
        );
    }
}