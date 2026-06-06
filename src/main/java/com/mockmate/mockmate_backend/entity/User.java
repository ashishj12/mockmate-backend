package com.mockmate.mockmate_backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "clerk_user_id", nullable = false, unique = true)
	private String clerkUserId;

	private String email;

	private String name;

	@Column(name = "image_url")
	private String imageUrl;

	private String industry;

	private String bio;

	private Integer experience;

	@Column(columnDefinition = "TEXT")
	private String skills;

	@OneToOne(mappedBy = "user")
	private Resume resume;

	@OneToMany(mappedBy = "user")
	private List<CoverLetter> coverLetters = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Assessment> assessments = new ArrayList<>();

	@OneToOne(mappedBy = "user")
	private IndustryInsight industryInsight;
}