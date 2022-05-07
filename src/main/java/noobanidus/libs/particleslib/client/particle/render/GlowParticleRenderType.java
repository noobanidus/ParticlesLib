package noobanidus.libs.particleslib.client.particle.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleRenderType;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import noobanidus.libs.particleslib.client.events.RenderTickHandler;
import noobanidus.libs.particleslib.client.render.DelayedRender;
import noobanidus.libs.particleslib.client.util.RenderUtil;
import noobanidus.libs.particleslib.init.ModShaders;
import org.lwjgl.opengl.GL11;

public class GlowParticleRenderType implements ParticleRenderType {
    public static final GlowParticleRenderType INSTANCE = new GlowParticleRenderType();

    private static void beginRenderCommon(BufferBuilder bufferBuilder, TextureManager textureManager) {
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        RenderSystem.setShader(ModShaders::getGlowingSpriteShader);
        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
        RenderTickHandler.particleMVMatrix = RenderSystem.getModelViewMatrix();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
    }

    private static void endRenderCommon() {
        Minecraft.getInstance().textureManager.getTexture(TextureAtlas.LOCATION_PARTICLES).restoreLastBlurMipmap();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
    }

    @Override
    public void begin(BufferBuilder b, TextureManager tex) {
        beginRenderCommon(b, tex);
    }

    @Override
    public void end(Tesselator t) {
        t.end();
        DelayedRender.getDelayedRender().getBuffer(RenderUtil.GLOWING_PARTICLE);
        RenderSystem.enableDepthTest();
        endRenderCommon();
    }
}
