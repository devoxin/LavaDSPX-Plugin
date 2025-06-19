# LavaDSPX-Plugin
A Lavalink plugin for enabling [LavaDSPX](https://github.com/devoxin/LavaDSPX) filters.

## How to use LavaDSPX-Plugin

```yml
lavalink:
  plugins:
    - dependency: com.github.Devoxin:LavaDSPX-Plugin:{VERSION} # replace {VERSION} with the latest version from the "Releases" tab.
      repository: https://jitpack.io
```

Once enabled, you can then send a [player update](https://lavalink.dev/api/rest#update-player) with the desired filters, e.g.
```js
{
  "filters": {
    "pluginFilters": {
      "high-pass": { // Cuts off frequencies lower than the specified {cutoffFrequency}.
        "cutoffFrequency": 80 // Integer, higher than 0, in Hz.
        "boostFactor": 1.0    // Float, higher than 0.0. This alters volume output. A value of 1.0 means no volume change.
      },
      "low-pass": { // Cuts off frequencies higher than the specified {cutoffFrequency}.
        "cutoffFrequency": 80 // Integer, higher than 0, in Hz.
        "boostFactor": 1.0    // Float, higher than 0.0. This alters volume output. A value of 1.0 means no volume change.
      },
      "normalization": { // Attenuates peaking where peaks are defined as having a higher value than {maxAmplitude}. 
        "maxAmplitude": 0.5 // Float, within the range of 0.0 - 1.0. A value of 0.0 mutes the output.
        "adaptive": true    // Boolean, whether peak amplitudes should persist. Refer to the note below for more information.
      },
      "echo": { // Self-explanatory; provides an echo effect.
        "echoLength": 0.3, // Float, higher than 0.0, in seconds (1.0 = 1 second).
        "decay": 0.5       // Float, within the range of 0.0 - 1.0. A value of 1.0 means no decay, and a value of 0.0 means
                           // immediate decay (no echo effect).
    }
  }
}
```

> [!NOTE]
> You can find out more about how the `NormalizationFilter`'s `adaptive` setting works [here](https://github.com/Devoxin/LavaDSPX/blob/main/src/main/java/me/devoxin/lavadspx/NormalizationFilter.java#L39-L43). 

## How to get help

Join the [Lavalink Discord Server](https://discord.gg/wYfsW4HNjb).
