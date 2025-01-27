/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.ai.bedrock.jurassic2.api;

// @formatter:off

import java.time.Duration;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;

import org.springframework.ai.bedrock.api.AbstractBedrockApi;
import org.springframework.ai.bedrock.jurassic2.api.Ai21Jurassic2ChatBedrockApi.Ai21Jurassic2ChatRequest;
import org.springframework.ai.bedrock.jurassic2.api.Ai21Jurassic2ChatBedrockApi.Ai21Jurassic2ChatResponse;
import org.springframework.ai.model.ChatModelDescription;

/**
 * Java client for the Bedrock Jurassic2 chat model.
 * https://docs.aws.amazon.com/bedrock/latest/userguide/model-parameters-jurassic2.html
 *
 * @author Christian Tzolov
 * @author Thomas Vitale
 * @author Wei Jiang
 * @since 0.8.0
 */
public class Ai21Jurassic2ChatBedrockApi extends
		AbstractBedrockApi<Ai21Jurassic2ChatRequest, Ai21Jurassic2ChatResponse, Ai21Jurassic2ChatResponse> {

	/**
	 * Create a new Ai21Jurassic2ChatBedrockApi instance using the default credentials provider chain, the default
	 * object mapper, default temperature and topP values.
	 *
	 * @param modelId The model id to use. See the {@link Ai21Jurassic2ChatModel} for the supported models.
	 * @param region The AWS region to use.
	 */
	public Ai21Jurassic2ChatBedrockApi(String modelId, String region) {
		super(modelId, region);
	}


	/**
	 * Create a new Ai21Jurassic2ChatBedrockApi instance.
	 *
	 * @param modelId The model id to use. See the {@link Ai21Jurassic2ChatBedrockApi.Ai21Jurassic2ChatModel} for the supported models.
	 * @param credentialsProvider The credentials provider to connect to AWS.
	 * @param region The AWS region to use.
	 * @param objectMapper The object mapper to use for JSON serialization and deserialization.
	 */
	public Ai21Jurassic2ChatBedrockApi(String modelId, AwsCredentialsProvider credentialsProvider, String region,
									ObjectMapper objectMapper) {
		super(modelId, credentialsProvider, region, objectMapper);
	}

	/**
	 * Create a new Ai21Jurassic2ChatBedrockApi instance using the default credentials provider chain, the default
	 * object mapper, default temperature and topP values.
	 *
	 * @param modelId The model id to use. See the {@link Ai21Jurassic2ChatModel} for the supported models.
	 * @param region The AWS region to use.
	 * @param timeout The timeout to use.
	 */
	public Ai21Jurassic2ChatBedrockApi(String modelId, String region, Duration timeout) {
		super(modelId, region, timeout);
	}


	/**
	 * Create a new Ai21Jurassic2ChatBedrockApi instance.
	 *
	 * @param modelId The model id to use. See the {@link Ai21Jurassic2ChatBedrockApi.Ai21Jurassic2ChatModel} for the supported models.
	 * @param credentialsProvider The credentials provider to connect to AWS.
	 * @param region The AWS region to use.
	 * @param objectMapper The object mapper to use for JSON serialization and deserialization.
	 * @param timeout The timeout to use.
	 */
	public Ai21Jurassic2ChatBedrockApi(String modelId, AwsCredentialsProvider credentialsProvider, String region,
									ObjectMapper objectMapper, Duration timeout) {
		super(modelId, credentialsProvider, region, objectMapper, timeout);
	}

	/**
	 * Create a new Ai21Jurassic2ChatBedrockApi instance.
	 *
	 * @param modelId The model id to use. See the {@link Ai21Jurassic2ChatBedrockApi.Ai21Jurassic2ChatModel} for the supported models.
	 * @param credentialsProvider The credentials provider to connect to AWS.
	 * @param region The AWS region to use.
	 * @param objectMapper The object mapper to use for JSON serialization and deserialization.
	 * @param timeout The timeout to use.
	 */
	public Ai21Jurassic2ChatBedrockApi(String modelId, AwsCredentialsProvider credentialsProvider, Region region,
									ObjectMapper objectMapper, Duration timeout) {
		super(modelId, credentialsProvider, region, objectMapper, timeout);
	}

	@Override
	public Ai21Jurassic2ChatResponse chatCompletion(Ai21Jurassic2ChatRequest request) {
		return this.internalInvocation(request, Ai21Jurassic2ChatResponse.class);
	}

	/**
	 * Ai21 Jurassic2 models version.
	 */
	public enum Ai21Jurassic2ChatModel implements ChatModelDescription {

		/**
		 * ai21.j2-mid-v1
		 */
		AI21_J2_MID_V1("ai21.j2-mid-v1"),

		/**
		 * ai21.j2-ultra-v1
		 */
		AI21_J2_ULTRA_V1("ai21.j2-ultra-v1");

		private final String id;

		Ai21Jurassic2ChatModel(String value) {
			this.id = value;
		}

		/**
		 * The model id.
		 * @return the model id.
		 */
		public String id() {
			return this.id;
		}

		/**
		 * The model id.
		 * @return the model id.
		 */
		@Override
		public String getName() {
			return this.id;
		}
	}

	/**
	 * AI21 Jurassic2 chat request parameters.
	 *
	 * @param prompt The prompt to use for the chat.
	 * @param temperature The temperature value controls the randomness of the generated text.
	 * @param topP The topP value controls the diversity of the generated text. Use a lower value to ignore less
	 * probable options.
	 * @param maxTokens Specify the maximum number of tokens to use in the generated response.
	 * @param stopSequences Configure stop sequences that the model recognizes and after which it stops generating
	 * further tokens. Press the Enter key to insert a newline character in a stop sequence. Use the Tab key to finish
	 * inserting a stop sequence.
	 * @param countPenalty Control repetition in the generated response. Use a higher value to lower the probability of
	 * generating new tokens that already appear at least once in the prompt or in the completion. Proportional to the
	 * number of appearances.
	 * @param presencePenalty Control repetition in the generated response. Use a higher value to lower the probability
	 * of generating new tokens that already appear at least once in the prompt or in the completion.
	 * @param frequencyPenalty Control repetition in the generated response. Use a high value to lower the probability
	 * of generating new tokens that already appear at least once in the prompt or in the completion. The value is
	 * proportional to the frequency of the token appearances (normalized to text length).
	 */
	@JsonInclude(Include.NON_NULL)
	public record Ai21Jurassic2ChatRequest(
			@JsonProperty("prompt") String prompt,
			@JsonProperty("temperature") Double temperature,
			@JsonProperty("topP") Double topP,
			@JsonProperty("maxTokens") Integer maxTokens,
			@JsonProperty("stopSequences") List<String> stopSequences,
			@JsonProperty("countPenalty") IntegerScalePenalty countPenalty,
			@JsonProperty("presencePenalty") FloatScalePenalty presencePenalty,
			@JsonProperty("frequencyPenalty") IntegerScalePenalty frequencyPenalty) {

		/**
		 * Create a new {@link Ai21Jurassic2ChatRequest} instance.
		 * @param prompt The prompt to use for the chat.
		 * @return a new {@link Ai21Jurassic2ChatRequest} instance.
		 */
		public static Builder builder(String prompt) {
			return new Builder(prompt);
		}

		/**
		 * Penalty with integer scale value.
		 *
		 * @param scale The scale value controls the strength of the penalty. Use a higher value to lower the
		 * probability of generating new tokens that already appear at least once in the prompt or in the completion.
		 * @param applyToWhitespaces Reduce the probability of repetition of special characters. A true value applies
		 * the penalty to whitespaces and new lines.
		 * @param applyToPunctuations Reduce the probability of repetition of special characters. A true value applies
		 * the penalty to punctuations.
		 * @param applyToNumbers Reduce the probability of repetition of special characters. A true value applies the
		 * penalty to numbers.
		 * @param applyToStopwords Reduce the probability of repetition of special characters. A true value applies the
		 * penalty to stopwords.
		 * @param applyToEmojis Reduce the probability of repetition of special characters. A true value applies the
		 * penalty to emojis.
		 */
		@JsonInclude(Include.NON_NULL)
		public record IntegerScalePenalty(
				@JsonProperty("scale") Integer scale,
				@JsonProperty("applyToWhitespaces") boolean applyToWhitespaces,
				@JsonProperty("applyToPunctuations") boolean applyToPunctuations,
				@JsonProperty("applyToNumbers") boolean applyToNumbers,
				@JsonProperty("applyToStopwords") boolean applyToStopwords,
				@JsonProperty("applyToEmojis") boolean applyToEmojis) {
		}

		/**
		 * Penalty with float scale value.
		 *
		 * @param scale The scale value controls the strength of the penalty. Use a higher value to lower the
		 * probability of generating new tokens that already appear at least once in the prompt or in the completion.
		 * @param applyToWhitespaces Reduce the probability of repetition of special characters. A true value applies
		 * the penalty to whitespaces and new lines.
		 * @param applyToPunctuations Reduce the probability of repetition of special characters. A true value applies
		 * the penalty to punctuations.
		 * @param applyToNumbers Reduce the probability of repetition of special characters. A true value applies the
		 * penalty to numbers.
		 * @param applyToStopwords Reduce the probability of repetition of special characters. A true value applies the
		 * penalty to stopwords.
		 * @param applyToEmojis Reduce the probability of repetition of special characters. A true value applies the
		 * penalty to emojis.
		 */
		@JsonInclude(Include.NON_NULL)
		public record FloatScalePenalty(@JsonProperty("scale") Float scale,
				@JsonProperty("applyToWhitespaces") boolean applyToWhitespaces,
				@JsonProperty("applyToPunctuations") boolean applyToPunctuations,
				@JsonProperty("applyToNumbers") boolean applyToNumbers,
				@JsonProperty("applyToStopwords") boolean applyToStopwords,
				@JsonProperty("applyToEmojis") boolean applyToEmojis) {
		}

		/**
		 * Builder for {@link Ai21Jurassic2ChatRequest}.
		 */
		public static final class Builder {
			private String prompt;
			private Double temperature;
			private Double topP;
			private Integer maxTokens;
			private List<String> stopSequences;
			private IntegerScalePenalty countPenalty;
			private FloatScalePenalty presencePenalty;
			private IntegerScalePenalty frequencyPenalty;

			private Builder(String prompt) {
				this.prompt = prompt;
			}

			/**
			 * Set the temperature.
			 * @param temperature the temperature
			 * @return this {@link Builder} instance
			 */
			public Builder temperature(Double temperature) {
				this.temperature = temperature;
				return this;
			}

			/**
			 * Set the topP.
			 * @param topP the topP
			 * @return this {@link Builder} instance
			 */
			public Builder topP(Double topP) {
				this.topP = topP;
				return this;
			}

			/**
			 * Set the maxTokens.
			 * @param maxTokens the maxTokens
			 * @return this {@link Builder} instance
			 */
			public Builder maxTokens(Integer maxTokens) {
				this.maxTokens = maxTokens;
				return this;
			}

			/**
			 * Set the stopSequences.
			 * @param stopSequences the stopSequences
			 * @return this {@link Builder} instance
			 */
			public Builder stopSequences(List<String> stopSequences) {
				this.stopSequences = stopSequences;
				return this;
			}

			/**
			 * Set the countPenalty.
			 * @param countPenalty the countPenalty
			 * @return this {@link Builder} instance
			 */
			public Builder countPenalty(IntegerScalePenalty countPenalty) {
				this.countPenalty = countPenalty;
				return this;
			}

			/**
			 * Set the presencePenalty.
			 * @param presencePenalty the presencePenalty
			 * @return this {@link Builder} instance
			 */
			public Builder presencePenalty(FloatScalePenalty presencePenalty) {
				this.presencePenalty = presencePenalty;
				return this;
			}

			/**
			 * Set the frequencyPenalty.
			 * @param frequencyPenalty the frequencyPenalty
			 * @return this {@link Builder} instance
			 */
			public Builder frequencyPenalty(IntegerScalePenalty frequencyPenalty) {
				this.frequencyPenalty = frequencyPenalty;
				return this;
			}

			/**
			 * Build the {@link Ai21Jurassic2ChatRequest} instance.
			 * @return a new {@link Ai21Jurassic2ChatRequest} instance
			 */
			public Ai21Jurassic2ChatRequest build() {
				return new Ai21Jurassic2ChatRequest(
						this.prompt,
						this.temperature,
						this.topP,
						this.maxTokens,
						this.stopSequences,
						this.countPenalty,
						this.presencePenalty,
						this.frequencyPenalty
				);
			}
		}
	}

	/**
	 * Ai21 Jurassic2 chat response.
	 * https://docs.ai21.com/reference/j2-complete-api-ref#response
	 *
	 * @param id The unique identifier of the response.
	 * @param prompt The prompt used for the chat.
	 * @param completions The completions generated by the model.
	 * @param amazonBedrockInvocationMetrics The metrics about the model invocation.
	 */
	@JsonInclude(Include.NON_NULL)
	public record Ai21Jurassic2ChatResponse(
			@JsonProperty("id") String id,
			@JsonProperty("prompt") Prompt prompt,
			@JsonProperty("completions") List<Completion> completions,
			@JsonProperty("amazon-bedrock-invocationMetrics") AmazonBedrockInvocationMetrics amazonBedrockInvocationMetrics) {

		/**
		 * The completions generated by the model.
		 * @param data The data field contains the prompt used for the completion.
		 * @param finishReason The finishReason field provides detailed information about why the completion was halted.
		 */
		@JsonInclude(Include.NON_NULL)
		public record Completion(
				@JsonProperty("data") Prompt data,
				@JsonProperty("finishReason") FinishReason finishReason) {
		}

		/**
		 * Provides detailed information about each token in both the prompt and the completions.
		 *
		 * @param generatedToken The generatedToken fields.
		 * @param topTokens The topTokens field is a list of the top K alternative tokens for this position, sorted by
		 * probability, according to the topKReturn request parameter. If topKReturn is set to 0, this field will be
		 * null.
		 * @param textRange The textRange field indicates the start and end offsets of the token in the decoded text
		 * string.
		 */
		@JsonInclude(Include.NON_NULL)
		public record Token(
				@JsonProperty("generatedToken") GeneratedToken generatedToken,
				@JsonProperty("topTokens") List<TopToken> topTokens,
				@JsonProperty("textRange") TextRange textRange) {
		}

		/**
		 * The generatedToken fields.
		 *
		 * @param token TThe string representation of the token.
		 * @param logprob The predicted log probability of the token after applying the sampling parameters as a float
		 * value.
		 * @param rawLogprob The raw predicted log probability of the token as a float value. For the indifferent values
		 * (namely, temperature=1, topP=1) we get raw_logprob=logprob.
		 */
		@JsonInclude(Include.NON_NULL)
		public record GeneratedToken(
				@JsonProperty("token") String token,
				@JsonProperty("logprob") Float logprob,
				@JsonProperty("raw_logprob") Float rawLogprob) {

		}

		/**
		 * The topTokens field is a list of the top K alternative tokens for this position, sorted by probability,
		 * according to the topKReturn request parameter. If topKReturn is set to 0, this field will be null.
		 *
		 * @param token The string representation of the alternative token.
		 * @param logprob The predicted log probability of the alternative token.
		 */
		@JsonInclude(Include.NON_NULL)
		public record TopToken(
				@JsonProperty("token") String token,
				@JsonProperty("logprob") Float logprob) {
		}

		/**
		 * The textRange field indicates the start and end offsets of the token in the decoded text string.
		 *
		 * @param start The starting index of the token in the decoded text string.
		 * @param end The ending index of the token in the decoded text string.
		 */
		@JsonInclude(Include.NON_NULL)
		public record TextRange(
				@JsonProperty("start") Integer start,
				@JsonProperty("end") Integer end) {
		}

		/**
		 * The prompt includes the raw text, the tokens with their log probabilities, and the top-K alternative tokens
		 * at each position, if requested.
		 *
		 * @param text The raw text of the prompt.
		 * @param tokens Provides detailed information about each token in both the prompt and the completions.
		 */
		@JsonInclude(Include.NON_NULL)
		public record Prompt(
				@JsonProperty("text") String text,
				@JsonProperty("tokens") List<Token> tokens) {
		}

		/**
		 * Explains why the generation process was halted for a specific completion.
		 *
		 * @param reason The reason field indicates the reason for the completion to stop.
		 * @param length The length field indicates the number of tokens generated in the completion.
		 * @param sequence The sequence field indicates the stop sequence that caused the completion to stop.
		 */
		@JsonInclude(Include.NON_NULL)
		public record FinishReason(
				@JsonProperty("reason") String reason,
				@JsonProperty("length") String length,
				@JsonProperty("sequence") String sequence) {
		}
	}


}
// @formatter:on
