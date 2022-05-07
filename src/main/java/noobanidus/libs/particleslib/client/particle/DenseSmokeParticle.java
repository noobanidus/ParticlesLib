package noobanidus.libs.particleslib.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import noobanidus.libs.particleslib.client.particle.base.GenericParticle;
import noobanidus.libs.particleslib.client.particle.base.WhirlwindParticle;
import noobanidus.libs.particleslib.client.particle.data.GenericParticleData;
import noobanidus.libs.particleslib.client.particle.data.WhirlwindParticleData;
import noobanidus.libs.particleslib.client.particle.render.GlowParticleRenderType;
import noobanidus.libs.particleslib.client.particle.render.SpriteParticleRenderType;
import noobanidus.libs.particleslib.client.util.RenderUtil;

public class DenseSmokeParticle extends GenericParticle {
  public DenseSmokeParticle(ClientLevel world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
    super(world, data, x, y, z, vx, vy, vz, data.additive ? GlowParticleRenderType.INSTANCE : SpriteParticleRenderType.INSTANCE, data.additive ? RenderUtil.GLOWING_PARTICLE : RenderUtil.DELAYED_PARTICLE);
  }
}
