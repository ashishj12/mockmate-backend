package com.mockmate.mockmate_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ats_analysis")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtsAnalysis extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer score;

	@Column(columnDefinition = "jsonb")
	private String keywords;

	@Column(name = "missing_keywords", columnDefinition = "jsonb")
	private String missingKeywords;

	@Column(columnDefinition = "jsonb")
	private String suggestions;

	@Column(name = "raw_response", columnDefinition = "jsonb")
	private String rawResponse;

	@OneToOne
	@JoinColumn(name = "resume_id", nullable = false)
	private Resume resume;
}