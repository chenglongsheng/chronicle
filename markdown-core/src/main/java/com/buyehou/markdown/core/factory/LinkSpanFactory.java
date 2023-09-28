package com.buyehou.markdown.core.factory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.buyehou.markdown.MarkwonConfiguration;
import com.buyehou.markdown.RenderProps;
import com.buyehou.markdown.SpanFactory;
import com.buyehou.markdown.core.CoreProps;
import com.buyehou.markdown.core.spans.LinkSpan;

public class LinkSpanFactory implements SpanFactory {
    @Nullable
    @Override
    public Object getSpans(@NonNull MarkwonConfiguration configuration, @NonNull RenderProps props) {
        return new LinkSpan(
                configuration.theme(),
                CoreProps.LINK_DESTINATION.require(props),
                configuration.linkResolver()
        );
    }
}
