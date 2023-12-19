package me.devoxin.lavadspx.plugin;

import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import dev.arbjerg.lavalink.api.AudioFilterExtension;
import kotlinx.serialization.json.JsonElement;
import me.devoxin.lavadspx.NormalizationFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NormalizationFilterExtension implements AudioFilterExtension {
    private static final Logger LOG = LoggerFactory.getLogger(NormalizationFilterExtension.class);

    public NormalizationFilterExtension() {
        LOG.info("Loaded audio filter: normalization");
    }

    @NotNull
    @Override
    public String getName() {
        return "normalization";
    }

    @Nullable
    @Override
    public FloatPcmAudioFilter build(@NotNull JsonElement data, @Nullable AudioDataFormat format, @Nullable FloatPcmAudioFilter output) {
        if (format == null || output == null) {
            return null;
        }

        Float maxAmplitude = PrimitiveUtils.parseFloatElement(data, "maxAmplitude");
        Boolean adaptive = PrimitiveUtils.parseBooleanElement(data, "adaptive");

        if (maxAmplitude == null || maxAmplitude <= 0.0f) {
            return null;
        }

        if (adaptive != null) {
            return new NormalizationFilter(output, maxAmplitude, adaptive);
        } else {
            return new NormalizationFilter(output, maxAmplitude);
        }
    }

    @Override
    public boolean isEnabled(@NotNull JsonElement data) {
        Float value = PrimitiveUtils.parseFloatElement(data, "maxAmplitude");
        return value != null && value > 0.0f;
    }
}
