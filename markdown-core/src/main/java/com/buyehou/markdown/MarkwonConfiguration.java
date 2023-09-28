package com.buyehou.markdown;

import androidx.annotation.NonNull;

import com.buyehou.markdown.core.MarkwonTheme;
import com.buyehou.markdown.image.AsyncDrawableLoader;
import com.buyehou.markdown.image.ImageSizeResolver;
import com.buyehou.markdown.image.ImageSizeResolverDef;
import com.buyehou.markdown.image.destination.ImageDestinationProcessor;
import com.buyehou.markdown.syntax.SyntaxHighlight;
import com.buyehou.markdown.syntax.SyntaxHighlightNoOp;

/**
 * since 3.0.0 renamed `SpannableConfiguration` -&gt; `MarkwonConfiguration`
 */
public class MarkwonConfiguration {

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    private final MarkwonTheme theme;
    private final AsyncDrawableLoader asyncDrawableLoader;
    private final SyntaxHighlight syntaxHighlight;
    private final LinkResolver linkResolver;
    // @since 4.4.0
    private final ImageDestinationProcessor imageDestinationProcessor;
    private final ImageSizeResolver imageSizeResolver;

    // @since 3.0.0
    private final MarkwonSpansFactory spansFactory;

    private MarkwonConfiguration(@NonNull Builder builder) {
        this.theme = builder.theme;
        this.asyncDrawableLoader = builder.asyncDrawableLoader;
        this.syntaxHighlight = builder.syntaxHighlight;
        this.linkResolver = builder.linkResolver;
        this.imageDestinationProcessor = builder.imageDestinationProcessor;
        this.imageSizeResolver = builder.imageSizeResolver;
        this.spansFactory = builder.spansFactory;
    }

    @NonNull
    public MarkwonTheme theme() {
        return theme;
    }

    @NonNull
    public AsyncDrawableLoader asyncDrawableLoader() {
        return asyncDrawableLoader;
    }

    @NonNull
    public SyntaxHighlight syntaxHighlight() {
        return syntaxHighlight;
    }

    @NonNull
    public LinkResolver linkResolver() {
        return linkResolver;
    }

    /**
     * @since 4.4.0
     */
    @NonNull
    public ImageDestinationProcessor imageDestinationProcessor() {
        return imageDestinationProcessor;
    }

    @NonNull
    public ImageSizeResolver imageSizeResolver() {
        return imageSizeResolver;
    }

    /**
     * @since 3.0.0
     */
    @NonNull
    public MarkwonSpansFactory spansFactory() {
        return spansFactory;
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public static class Builder {

        private MarkwonTheme theme;
        private AsyncDrawableLoader asyncDrawableLoader;
        private SyntaxHighlight syntaxHighlight;
        private LinkResolver linkResolver;
        // @since 4.4.0
        private ImageDestinationProcessor imageDestinationProcessor;
        private ImageSizeResolver imageSizeResolver;
        private MarkwonSpansFactory spansFactory;

        Builder() {
        }

        /**
         * @since 4.0.0
         */
        @NonNull
        public Builder asyncDrawableLoader(@NonNull AsyncDrawableLoader asyncDrawableLoader) {
            this.asyncDrawableLoader = asyncDrawableLoader;
            return this;
        }

        @NonNull
        public Builder syntaxHighlight(@NonNull SyntaxHighlight syntaxHighlight) {
            this.syntaxHighlight = syntaxHighlight;
            return this;
        }

        @NonNull
        public Builder linkResolver(@NonNull LinkResolver linkResolver) {
            this.linkResolver = linkResolver;
            return this;
        }

        /**
         * @since 4.4.0
         */
        @NonNull
        public Builder imageDestinationProcessor(@NonNull ImageDestinationProcessor imageDestinationProcessor) {
            this.imageDestinationProcessor = imageDestinationProcessor;
            return this;
        }

        /**
         * @since 1.0.1
         */
        @NonNull
        public Builder imageSizeResolver(@NonNull ImageSizeResolver imageSizeResolver) {
            this.imageSizeResolver = imageSizeResolver;
            return this;
        }

        @NonNull
        public MarkwonConfiguration build(
                @NonNull MarkwonTheme theme,
                @NonNull MarkwonSpansFactory spansFactory) {

            this.theme = theme;
            this.spansFactory = spansFactory;

            // @since 4.0.0
            if (asyncDrawableLoader == null) {
                asyncDrawableLoader = AsyncDrawableLoader.noOp();
            }

            if (syntaxHighlight == null) {
                syntaxHighlight = new SyntaxHighlightNoOp();
            }

            if (linkResolver == null) {
                linkResolver = new LinkResolverDef();
            }

            // @since 4.4.0
            if (imageDestinationProcessor == null) {
                imageDestinationProcessor = ImageDestinationProcessor.noOp();
            }

            if (imageSizeResolver == null) {
                imageSizeResolver = new ImageSizeResolverDef();
            }

            return new MarkwonConfiguration(this);
        }
    }

}
