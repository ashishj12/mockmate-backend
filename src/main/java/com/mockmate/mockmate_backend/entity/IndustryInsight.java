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
@Table(name = "industry_insights")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndustryInsight extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String industry;

	@Column(name = "salary_ranges", columnDefinition = "jsonb")
	private String salaryRanges;

	@Column(name = "growth_rate")
	private String growthRate;

	@Column(name = "demand_level")
	private String demandLevel;

	@Column(name = "top_skills", columnDefinition = "jsonb")
	private String topSkills;

	@Column(name = "market_outlook", columnDefinition = "TEXT")
	private String marketOutlook;

	@Column(name = "key_trends", columnDefinition = "jsonb")
	private String keyTrends;

	@Column(name = "recommended_skills", columnDefinition = "jsonb")
	private String recommendedSkills;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}