package me.devoxin.lavadspx.plugin;

import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import dev.arbjerg.lavalink.api.AudioFilterExtension;
import kotlinx.serialization.json.JsonElement;
import me.devoxin.lavadspx.EchoFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EchoFilterExtension implements AudioFilterExtension {
    private static final Logger LOG = LoggerFactory.getLogger(EchoFilterExtension.class);

    public EchoFilterExtension() {
        LOG.info("Loaded audio filter: echo");
    }

    @NotNull
    @Override
    public String getName() {
        return "echo";
    }

    @Nullable
    @Override
    public FloatPcmAudioFilter build(@NotNull JsonElement data, @Nullable AudioDataFormat format, @Nullable FloatPcmAudioFilter output) {
        if (format == null || output == null) {
            return null;
        }

        Float echoLength = PrimitiveUtils.parseFloatElement(data, "echoLength");
        Float decay = PrimitiveUtils.parseFloatElement(data, "decay");

        if (echoLength == null || echoLength <= 0.0f || decay == null || decay <= 0.0f) {
            return null;
        }

        return new EchoFilter(output, format.sampleRate, format.channelCount, echoLength, decay);
    }

    @Override
    public boolean isEnabled(@NotNull JsonElement data) {
        Float echoLength = PrimitiveUtils.parseFloatElement(data, "echoLength");
        Float decay = PrimitiveUtils.parseFloatElement(data, "decay");
        return echoLength != null && echoLength > 0 && decay != null && decay > 0;
    }
}
