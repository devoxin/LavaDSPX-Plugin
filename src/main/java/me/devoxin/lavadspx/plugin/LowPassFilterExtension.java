package me.devoxin.lavadspx.plugin;

import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import dev.arbjerg.lavalink.api.AudioFilterExtension;
import kotlinx.serialization.json.JsonElement;
import me.devoxin.lavadspx.LowPassFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LowPassFilterExtension implements AudioFilterExtension {
    private static final Logger LOG = LoggerFactory.getLogger(LowPassFilterExtension.class);

    public LowPassFilterExtension() {
        LOG.info("Loaded audio filter: low-pass");
    }

    @NotNull
    @Override
    public String getName() {
        return "low-pass";
    }

    @Nullable
    @Override
    public FloatPcmAudioFilter build(@NotNull JsonElement data, @Nullable AudioDataFormat format, @Nullable FloatPcmAudioFilter output) {
        Integer cutoffFrequency = NumberUtils.parseIntElement(data, "cutoffFrequency");
        Float boostFactor = NumberUtils.parseFloatElement(data, "boostFactor");

        if (cutoffFrequency == null || cutoffFrequency <= 0) {
            return null;
        }

        if (format == null || output == null) {
            return null;
        }

        if (boostFactor == null) {
            return new LowPassFilter(output, format.sampleRate, format.channelCount, cutoffFrequency);
        }

        return new LowPassFilter(output, format.sampleRate, format.channelCount, cutoffFrequency, boostFactor);
    }

    @Override
    public boolean isEnabled(@NotNull JsonElement data) {
        Integer value = NumberUtils.parseIntElement(data, "cutoffFrequency");
        return value != null && value > 0;
    }
}
