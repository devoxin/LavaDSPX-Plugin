package me.devoxin.lavadspx.plugin;

import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import dev.arbjerg.lavalink.api.AudioFilterExtension;
import kotlinx.serialization.json.JsonElement;
import me.devoxin.lavadspx.FixedNormalizationFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FixedNormalizationFilterExtension implements AudioFilterExtension {
    private static final Logger LOG = LoggerFactory.getLogger(LowPassFilterExtension.class);

    public FixedNormalizationFilterExtension() {
        LOG.info("Loaded audio filter: fixed-normalization");
    }

    @NotNull
    @Override
    public String getName() {
        return "fixed-normalization";
    }

    @Nullable
    @Override
    public FloatPcmAudioFilter build(@NotNull JsonElement data, @Nullable AudioDataFormat format, @Nullable FloatPcmAudioFilter output) {
        Float maxAmplitude = NumberUtils.parseFloatElement(data, "maxAmplitude");

        if (maxAmplitude == null || maxAmplitude <= 0.0f) {
            return null;
        }

        if (format == null || output == null) {
            return null;
        }

        return new FixedNormalizationFilter(output, maxAmplitude);
    }

    @Override
    public boolean isEnabled(@NotNull JsonElement data) {
        Float value = NumberUtils.parseFloatElement(data, "maxAmplitude");
        return value != null && value > 0.0f;
    }
}
