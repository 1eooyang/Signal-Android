package org.thoughtcrime.securesms.search.model;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Represents the information for a single item in a {@link SearchResult}.
 */
public interface SearchResultInfo {

  Uri getThumbnailUri();

  String getTitle();

  String getSubtitle();

  String getSideDescriptor();

  Type getType();

  enum Type {
    CONVERSATION, MESSAGE, CONTACT
  }
}
