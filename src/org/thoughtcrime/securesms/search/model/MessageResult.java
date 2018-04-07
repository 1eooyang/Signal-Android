package org.thoughtcrime.securesms.search.model;

import android.support.annotation.NonNull;

import org.thoughtcrime.securesms.recipients.Recipient;

/**
 * Represents a search result for a message
 */
public class MessageResult {

  public final Recipient recipient;
  public final String    body;
  public final long      threadId;
  public final long      receivedTimestampMs;

  public MessageResult(@NonNull Recipient recipient, @NonNull String body, long threadId, long receivedTimestampMs) {
    this.recipient           = recipient;
    this.body                = body;
    this.threadId            = threadId;
    this.receivedTimestampMs = receivedTimestampMs;
  }
}
