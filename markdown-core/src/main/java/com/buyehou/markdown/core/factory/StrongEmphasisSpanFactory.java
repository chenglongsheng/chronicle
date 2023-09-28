package com.buyehou.markdown.core.factory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.buyehou.markdown.MarkwonConfiguration;
import com.buyehou.markdown.RenderProps;
import com.buyehou.markdown.SpanFactory;
import com.buyehou.markdown.core.spans.StrongEmphasisSpan;


public class StrongEmphasisSpanFactory implements SpanFactory {
    @Nullable
    @Override
    public Object getSpans(@NonNull MarkwonConfiguration configuration, @NonNull RenderProps props) {
        return new StrongEmphasisSpan();
    }
}
